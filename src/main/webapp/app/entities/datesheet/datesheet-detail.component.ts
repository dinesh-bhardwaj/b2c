import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs/Subscription';
import { JhiEventManager } from 'ng-jhipster';

import { Datesheet } from './datesheet.model';
import { DatesheetService } from './datesheet.service';

@Component({
    selector: 'jhi-datesheet-detail',
    templateUrl: './datesheet-detail.component.html'
})
export class DatesheetDetailComponent implements OnInit, OnDestroy {

    datesheet: Datesheet;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private datesheetService: DatesheetService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInDatesheets();
    }

    load(id) {
        this.datesheetService.find(id).subscribe((datesheet) => {
            this.datesheet = datesheet;
        });
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInDatesheets() {
        this.eventSubscriber = this.eventManager.subscribe(
            'datesheetListModification',
            (response) => this.load(this.datesheet.id)
        );
    }
}
