import { Component, OnInit, OnDestroy } from '@angular/core';
import { Subscription } from 'rxjs/Subscription';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { SchoolClass } from './school-class.model';
import { SchoolClassService } from './school-class.service';
import { Principal, ResponseWrapper } from '../../shared';

@Component({
    selector: 'jhi-school-class',
    templateUrl: './school-class.component.html'
})
export class SchoolClassComponent implements OnInit, OnDestroy {
schoolClasses: SchoolClass[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        private schoolClassService: SchoolClassService,
        private jhiAlertService: JhiAlertService,
        private eventManager: JhiEventManager,
        private principal: Principal
    ) {
    }

    loadAll() {
        this.schoolClassService.query().subscribe(
            (res: ResponseWrapper) => {
                this.schoolClasses = res.json;
            },
            (res: ResponseWrapper) => this.onError(res.json)
        );
    }
    ngOnInit() {
        this.loadAll();
        this.principal.identity().then((account) => {
            this.currentAccount = account;
        });
        this.registerChangeInSchoolClasses();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: SchoolClass) {
        return item.id;
    }
    registerChangeInSchoolClasses() {
        this.eventSubscriber = this.eventManager.subscribe('schoolClassListModification', (response) => this.loadAll());
    }

    private onError(error) {
        this.jhiAlertService.error(error.message, null, null);
    }
}
