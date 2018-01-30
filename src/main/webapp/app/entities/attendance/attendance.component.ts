import { Component, OnInit, OnDestroy } from '@angular/core';
import { Subscription } from 'rxjs/Subscription';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { Attendance } from './attendance.model';
import { AttendanceService } from './attendance.service';
import { Principal, ResponseWrapper } from '../../shared';

@Component({
    selector: 'jhi-attendance',
    templateUrl: './attendance.component.html'
})
export class AttendanceComponent implements OnInit, OnDestroy {
attendances: Attendance[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        private attendanceService: AttendanceService,
        private jhiAlertService: JhiAlertService,
        private eventManager: JhiEventManager,
        private principal: Principal
    ) {
    }

    loadAll() {
        this.attendanceService.query().subscribe(
            (res: ResponseWrapper) => {
                this.attendances = res.json;
            },
            (res: ResponseWrapper) => this.onError(res.json)
        );
    }
    ngOnInit() {
        this.loadAll();
        this.principal.identity().then((account) => {
            this.currentAccount = account;
        });
        this.registerChangeInAttendances();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: Attendance) {
        return item.id;
    }
    registerChangeInAttendances() {
        this.eventSubscriber = this.eventManager.subscribe('attendanceListModification', (response) => this.loadAll());
    }

    private onError(error) {
        this.jhiAlertService.error(error.message, null, null);
    }
}
