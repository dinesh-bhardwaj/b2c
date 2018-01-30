import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { Downloads } from './downloads.model';
import { DownloadsPopupService } from './downloads-popup.service';
import { DownloadsService } from './downloads.service';

@Component({
    selector: 'jhi-downloads-delete-dialog',
    templateUrl: './downloads-delete-dialog.component.html'
})
export class DownloadsDeleteDialogComponent {

    downloads: Downloads;

    constructor(
        private downloadsService: DownloadsService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.downloadsService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'downloadsListModification',
                content: 'Deleted an downloads'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-downloads-delete-popup',
    template: ''
})
export class DownloadsDeletePopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private downloadsPopupService: DownloadsPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.downloadsPopupService
                .open(DownloadsDeleteDialogComponent as Component, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
