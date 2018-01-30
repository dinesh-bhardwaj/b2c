import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Response } from '@angular/http';

import { Observable } from 'rxjs/Observable';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { Attendance } from './attendance.model';
import { AttendancePopupService } from './attendance-popup.service';
import { AttendanceService } from './attendance.service';
import { Student, StudentService } from '../student';
import { ResponseWrapper } from '../../shared';

@Component({
    selector: 'jhi-attendance-dialog',
    templateUrl: './attendance-dialog.component.html'
})
export class AttendanceDialogComponent implements OnInit {

    attendance: Attendance;
    isSaving: boolean;

    students: Student[];

    constructor(
        public activeModal: NgbActiveModal,
        private jhiAlertService: JhiAlertService,
        private attendanceService: AttendanceService,
        private studentService: StudentService,
        private eventManager: JhiEventManager
    ) {
    }

    ngOnInit() {
        this.isSaving = false;
        this.studentService.query()
            .subscribe((res: ResponseWrapper) => { this.students = res.json; }, (res: ResponseWrapper) => this.onError(res.json));
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    save() {
        this.isSaving = true;
        if (this.attendance.id !== undefined) {
            this.subscribeToSaveResponse(
                this.attendanceService.update(this.attendance));
        } else {
            this.subscribeToSaveResponse(
                this.attendanceService.create(this.attendance));
        }
    }

    private subscribeToSaveResponse(result: Observable<Attendance>) {
        result.subscribe((res: Attendance) =>
            this.onSaveSuccess(res), (res: Response) => this.onSaveError());
    }

    private onSaveSuccess(result: Attendance) {
        this.eventManager.broadcast({ name: 'attendanceListModification', content: 'OK'});
        this.isSaving = false;
        this.activeModal.dismiss(result);
    }

    private onSaveError() {
        this.isSaving = false;
    }

    private onError(error: any) {
        this.jhiAlertService.error(error.message, null, null);
    }

    trackStudentById(index: number, item: Student) {
        return item.id;
    }
}

@Component({
    selector: 'jhi-attendance-popup',
    template: ''
})
export class AttendancePopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private attendancePopupService: AttendancePopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.attendancePopupService
                    .open(AttendanceDialogComponent as Component, params['id']);
            } else {
                this.attendancePopupService
                    .open(AttendanceDialogComponent as Component);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
