import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Response } from '@angular/http';

import { Observable } from 'rxjs/Observable';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { Downloads } from './downloads.model';
import { DownloadsPopupService } from './downloads-popup.service';
import { DownloadsService } from './downloads.service';
import { SchoolClass, SchoolClassService } from '../school-class';
import { Section, SectionService } from '../section';
import { ResponseWrapper } from '../../shared';

@Component({
    selector: 'jhi-downloads-dialog',
    templateUrl: './downloads-dialog.component.html'
})
export class DownloadsDialogComponent implements OnInit {

    downloads: Downloads;
    isSaving: boolean;

    schoolclasses: SchoolClass[];

    sections: Section[];

    constructor(
        public activeModal: NgbActiveModal,
        private jhiAlertService: JhiAlertService,
        private downloadsService: DownloadsService,
        private schoolClassService: SchoolClassService,
        private sectionService: SectionService,
        private eventManager: JhiEventManager
    ) {
    }

    ngOnInit() {
        this.isSaving = false;
        this.schoolClassService.query()
            .subscribe((res: ResponseWrapper) => { this.schoolclasses = res.json; }, (res: ResponseWrapper) => this.onError(res.json));
        this.sectionService.query()
            .subscribe((res: ResponseWrapper) => { this.sections = res.json; }, (res: ResponseWrapper) => this.onError(res.json));
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    save() {
        this.isSaving = true;
        if (this.downloads.id !== undefined) {
            this.subscribeToSaveResponse(
                this.downloadsService.update(this.downloads));
        } else {
            this.subscribeToSaveResponse(
                this.downloadsService.create(this.downloads));
        }
    }

    private subscribeToSaveResponse(result: Observable<Downloads>) {
        result.subscribe((res: Downloads) =>
            this.onSaveSuccess(res), (res: Response) => this.onSaveError());
    }

    private onSaveSuccess(result: Downloads) {
        this.eventManager.broadcast({ name: 'downloadsListModification', content: 'OK'});
        this.isSaving = false;
        this.activeModal.dismiss(result);
    }

    private onSaveError() {
        this.isSaving = false;
    }

    private onError(error: any) {
        this.jhiAlertService.error(error.message, null, null);
    }

    trackSchoolClassById(index: number, item: SchoolClass) {
        return item.id;
    }

    trackSectionById(index: number, item: Section) {
        return item.id;
    }
}

@Component({
    selector: 'jhi-downloads-popup',
    template: ''
})
export class DownloadsPopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private downloadsPopupService: DownloadsPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.downloadsPopupService
                    .open(DownloadsDialogComponent as Component, params['id']);
            } else {
                this.downloadsPopupService
                    .open(DownloadsDialogComponent as Component);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
