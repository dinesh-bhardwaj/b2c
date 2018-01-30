import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs/Subscription';
import { JhiEventManager } from 'ng-jhipster';

import { Attendance } from './attendance.model';
import { AttendanceService } from './attendance.service';

@Component({
    selector: 'jhi-attendance-detail',
    templateUrl: './attendance-detail.component.html'
})
export class AttendanceDetailComponent implements OnInit, OnDestroy {

    attendance: Attendance;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private attendanceService: AttendanceService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInAttendances();
    }

    load(id) {
        this.attendanceService.find(id).subscribe((attendance) => {
            this.attendance = attendance;
        });
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInAttendances() {
        this.eventSubscriber = this.eventManager.subscribe(
            'attendanceListModification',
            (response) => this.load(this.attendance.id)
        );
    }
}
