import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { SchoolClass } from './school-class.model';
import { SchoolClassPopupService } from './school-class-popup.service';
import { SchoolClassService } from './school-class.service';

@Component({
    selector: 'jhi-school-class-delete-dialog',
    templateUrl: './school-class-delete-dialog.component.html'
})
export class SchoolClassDeleteDialogComponent {

    schoolClass: SchoolClass;

    constructor(
        private schoolClassService: SchoolClassService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.schoolClassService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'schoolClassListModification',
                content: 'Deleted an schoolClass'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-school-class-delete-popup',
    template: ''
})
export class SchoolClassDeletePopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private schoolClassPopupService: SchoolClassPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.schoolClassPopupService
                .open(SchoolClassDeleteDialogComponent as Component, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
