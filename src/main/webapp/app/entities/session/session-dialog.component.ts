import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Response } from '@angular/http';

import { Observable } from 'rxjs/Observable';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { Session } from './session.model';
import { SessionPopupService } from './session-popup.service';
import { SessionService } from './session.service';

@Component({
    selector: 'jhi-session-dialog',
    templateUrl: './session-dialog.component.html'
})
export class SessionDialogComponent implements OnInit {

    session: Session;
    isSaving: boolean;

    constructor(
        public activeModal: NgbActiveModal,
        private sessionService: SessionService,
        private eventManager: JhiEventManager
    ) {
    }

    ngOnInit() {
        this.isSaving = false;
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    save() {
        this.isSaving = true;
        if (this.session.id !== undefined) {
            this.subscribeToSaveResponse(
                this.sessionService.update(this.session));
        } else {
            this.subscribeToSaveResponse(
                this.sessionService.create(this.session));
        }
    }

    private subscribeToSaveResponse(result: Observable<Session>) {
        result.subscribe((res: Session) =>
            this.onSaveSuccess(res), (res: Response) => this.onSaveError());
    }

    private onSaveSuccess(result: Session) {
        this.eventManager.broadcast({ name: 'sessionListModification', content: 'OK'});
        this.isSaving = false;
        this.activeModal.dismiss(result);
    }

    private onSaveError() {
        this.isSaving = false;
    }
}

@Component({
    selector: 'jhi-session-popup',
    template: ''
})
export class SessionPopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private sessionPopupService: SessionPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.sessionPopupService
                    .open(SessionDialogComponent as Component, params['id']);
            } else {
                this.sessionPopupService
                    .open(SessionDialogComponent as Component);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
