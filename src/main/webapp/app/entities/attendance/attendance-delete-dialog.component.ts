import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { Attendance } from './attendance.model';
import { AttendancePopupService } from './attendance-popup.service';
import { AttendanceService } from './attendance.service';

@Component({
    selector: 'jhi-attendance-delete-dialog',
    templateUrl: './attendance-delete-dialog.component.html'
})
export class AttendanceDeleteDialogComponent {

    attendance: Attendance;

    constructor(
        private attendanceService: AttendanceService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.attendanceService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'attendanceListModification',
                content: 'Deleted an attendance'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-attendance-delete-popup',
    template: ''
})
export class AttendanceDeletePopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private attendancePopupService: AttendancePopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.attendancePopupService
                .open(AttendanceDeleteDialogComponent as Component, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
