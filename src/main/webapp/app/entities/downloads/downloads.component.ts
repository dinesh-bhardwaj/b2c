import { Component, OnInit, OnDestroy } from '@angular/core';
import { Subscription } from 'rxjs/Subscription';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { Downloads } from './downloads.model';
import { DownloadsService } from './downloads.service';
import { Principal, ResponseWrapper } from '../../shared';

@Component({
    selector: 'jhi-downloads',
    templateUrl: './downloads.component.html'
})
export class DownloadsComponent implements OnInit, OnDestroy {
downloads: Downloads[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        private downloadsService: DownloadsService,
        private jhiAlertService: JhiAlertService,
        private eventManager: JhiEventManager,
        private principal: Principal
    ) {
    }

    loadAll() {
        this.downloadsService.query().subscribe(
            (res: ResponseWrapper) => {
                this.downloads = res.json;
            },
            (res: ResponseWrapper) => this.onError(res.json)
        );
    }
    ngOnInit() {
        this.loadAll();
        this.principal.identity().then((account) => {
            this.currentAccount = account;
        });
        this.registerChangeInDownloads();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: Downloads) {
        return item.id;
    }
    registerChangeInDownloads() {
        this.eventSubscriber = this.eventManager.subscribe('downloadsListModification', (response) => this.loadAll());
    }

    private onError(error) {
        this.jhiAlertService.error(error.message, null, null);
    }
}
