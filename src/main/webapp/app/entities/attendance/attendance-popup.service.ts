import { Injectable, Component } from '@angular/core';
import { Router } from '@angular/router';
import { NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { DatePipe } from '@angular/common';
import { Attendance } from './attendance.model';
import { AttendanceService } from './attendance.service';

@Injectable()
export class AttendancePopupService {
    private ngbModalRef: NgbModalRef;

    constructor(
        private datePipe: DatePipe,
        private modalService: NgbModal,
        private router: Router,
        private attendanceService: AttendanceService

    ) {
        this.ngbModalRef = null;
    }

    open(component: Component, id?: number | any): Promise<NgbModalRef> {
        return new Promise<NgbModalRef>((resolve, reject) => {
            const isOpen = this.ngbModalRef !== null;
            if (isOpen) {
                resolve(this.ngbModalRef);
            }

            if (id) {
                this.attendanceService.find(id).subscribe((attendance) => {
                    attendance.date = this.datePipe
                        .transform(attendance.date, 'yyyy-MM-ddTHH:mm:ss');
                    this.ngbModalRef = this.attendanceModalRef(component, attendance);
                    resolve(this.ngbModalRef);
                });
            } else {
                // setTimeout used as a workaround for getting ExpressionChangedAfterItHasBeenCheckedError
                setTimeout(() => {
                    this.ngbModalRef = this.attendanceModalRef(component, new Attendance());
                    resolve(this.ngbModalRef);
                }, 0);
            }
        });
    }

    attendanceModalRef(component: Component, attendance: Attendance): NgbModalRef {
        const modalRef = this.modalService.open(component, { size: 'lg', backdrop: 'static'});
        modalRef.componentInstance.attendance = attendance;
        modalRef.result.then((result) => {
            this.router.navigate([{ outlets: { popup: null }}], { replaceUrl: true, queryParamsHandling: 'merge' });
            this.ngbModalRef = null;
        }, (reason) => {
            this.router.navigate([{ outlets: { popup: null }}], { replaceUrl: true, queryParamsHandling: 'merge' });
            this.ngbModalRef = null;
        });
        return modalRef;
    }
}
