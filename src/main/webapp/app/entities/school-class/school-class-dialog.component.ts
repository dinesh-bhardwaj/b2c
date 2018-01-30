import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Response } from '@angular/http';

import { Observable } from 'rxjs/Observable';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { SchoolClass } from './school-class.model';
import { SchoolClassPopupService } from './school-class-popup.service';
import { SchoolClassService } from './school-class.service';

@Component({
    selector: 'jhi-school-class-dialog',
    templateUrl: './school-class-dialog.component.html'
})
export class SchoolClassDialogComponent implements OnInit {

    schoolClass: SchoolClass;
    isSaving: boolean;

    constructor(
        public activeModal: NgbActiveModal,
        private schoolClassService: SchoolClassService,
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
        if (this.schoolClass.id !== undefined) {
            this.subscribeToSaveResponse(
                this.schoolClassService.update(this.schoolClass));
        } else {
            this.subscribeToSaveResponse(
                this.schoolClassService.create(this.schoolClass));
        }
    }

    private subscribeToSaveResponse(result: Observable<SchoolClass>) {
        result.subscribe((res: SchoolClass) =>
            this.onSaveSuccess(res), (res: Response) => this.onSaveError());
    }

    private onSaveSuccess(result: SchoolClass) {
        this.eventManager.broadcast({ name: 'schoolClassListModification', content: 'OK'});
        this.isSaving = false;
        this.activeModal.dismiss(result);
    }

    private onSaveError() {
        this.isSaving = false;
    }
}

@Component({
    selector: 'jhi-school-class-popup',
    template: ''
})
export class SchoolClassPopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private schoolClassPopupService: SchoolClassPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.schoolClassPopupService
                    .open(SchoolClassDialogComponent as Component, params['id']);
            } else {
                this.schoolClassPopupService
                    .open(SchoolClassDialogComponent as Component);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
