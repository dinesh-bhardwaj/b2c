import { Component, OnInit, OnDestroy } from '@angular/core';
import { Subscription } from 'rxjs/Subscription';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { Datesheet } from './datesheet.model';
import { DatesheetService } from './datesheet.service';
import { Principal, ResponseWrapper } from '../../shared';

@Component({
    selector: 'jhi-datesheet',
    templateUrl: './datesheet.component.html'
})
export class DatesheetComponent implements OnInit, OnDestroy {
datesheets: Datesheet[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        private datesheetService: DatesheetService,
        private jhiAlertService: JhiAlertService,
        private eventManager: JhiEventManager,
        private principal: Principal
    ) {
    }

    loadAll() {
        this.datesheetService.query().subscribe(
            (res: ResponseWrapper) => {
                this.datesheets = res.json;
            },
            (res: ResponseWrapper) => this.onError(res.json)
        );
    }
    ngOnInit() {
        this.loadAll();
        this.principal.identity().then((account) => {
            this.currentAccount = account;
        });
        this.registerChangeInDatesheets();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: Datesheet) {
        return item.id;
    }
    registerChangeInDatesheets() {
        this.eventSubscriber = this.eventManager.subscribe('datesheetListModification', (response) => this.loadAll());
    }

    private onError(error) {
        this.jhiAlertService.error(error.message, null, null);
    }
}
