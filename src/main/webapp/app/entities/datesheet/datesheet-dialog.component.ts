import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Response } from '@angular/http';

import { Observable } from 'rxjs/Observable';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { Datesheet } from './datesheet.model';
import { DatesheetPopupService } from './datesheet-popup.service';
import { DatesheetService } from './datesheet.service';
import { SchoolClass, SchoolClassService } from '../school-class';
import { Section, SectionService } from '../section';
import { ResponseWrapper } from '../../shared';

@Component({
    selector: 'jhi-datesheet-dialog',
    templateUrl: './datesheet-dialog.component.html'
})
export class DatesheetDialogComponent implements OnInit {

    datesheet: Datesheet;
    isSaving: boolean;

    schoolclasses: SchoolClass[];

    sections: Section[];

    constructor(
        public activeModal: NgbActiveModal,
        private jhiAlertService: JhiAlertService,
        private datesheetService: DatesheetService,
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
        if (this.datesheet.id !== undefined) {
            this.subscribeToSaveResponse(
                this.datesheetService.update(this.datesheet));
        } else {
            this.subscribeToSaveResponse(
                this.datesheetService.create(this.datesheet));
        }
    }

    private subscribeToSaveResponse(result: Observable<Datesheet>) {
        result.subscribe((res: Datesheet) =>
            this.onSaveSuccess(res), (res: Response) => this.onSaveError());
    }

    private onSaveSuccess(result: Datesheet) {
        this.eventManager.broadcast({ name: 'datesheetListModification', content: 'OK'});
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
    selector: 'jhi-datesheet-popup',
    template: ''
})
export class DatesheetPopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private datesheetPopupService: DatesheetPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.datesheetPopupService
                    .open(DatesheetDialogComponent as Component, params['id']);
            } else {
                this.datesheetPopupService
                    .open(DatesheetDialogComponent as Component);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
