import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { Datesheet } from './datesheet.model';
import { DatesheetPopupService } from './datesheet-popup.service';
import { DatesheetService } from './datesheet.service';

@Component({
    selector: 'jhi-datesheet-delete-dialog',
    templateUrl: './datesheet-delete-dialog.component.html'
})
export class DatesheetDeleteDialogComponent {

    datesheet: Datesheet;

    constructor(
        private datesheetService: DatesheetService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.datesheetService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'datesheetListModification',
                content: 'Deleted an datesheet'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-datesheet-delete-popup',
    template: ''
})
export class DatesheetDeletePopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private datesheetPopupService: DatesheetPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.datesheetPopupService
                .open(DatesheetDeleteDialogComponent as Component, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
