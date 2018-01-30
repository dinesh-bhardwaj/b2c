import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs/Subscription';
import { JhiEventManager } from 'ng-jhipster';

import { Downloads } from './downloads.model';
import { DownloadsService } from './downloads.service';

@Component({
    selector: 'jhi-downloads-detail',
    templateUrl: './downloads-detail.component.html'
})
export class DownloadsDetailComponent implements OnInit, OnDestroy {

    downloads: Downloads;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private downloadsService: DownloadsService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInDownloads();
    }

    load(id) {
        this.downloadsService.find(id).subscribe((downloads) => {
            this.downloads = downloads;
        });
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInDownloads() {
        this.eventSubscriber = this.eventManager.subscribe(
            'downloadsListModification',
            (response) => this.load(this.downloads.id)
        );
    }
}
