import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs/Subscription';
import { JhiEventManager } from 'ng-jhipster';

import { SchoolClass } from './school-class.model';
import { SchoolClassService } from './school-class.service';

@Component({
    selector: 'jhi-school-class-detail',
    templateUrl: './school-class-detail.component.html'
})
export class SchoolClassDetailComponent implements OnInit, OnDestroy {

    schoolClass: SchoolClass;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private schoolClassService: SchoolClassService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInSchoolClasses();
    }

    load(id) {
        this.schoolClassService.find(id).subscribe((schoolClass) => {
            this.schoolClass = schoolClass;
        });
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInSchoolClasses() {
        this.eventSubscriber = this.eventManager.subscribe(
            'schoolClassListModification',
            (response) => this.load(this.schoolClass.id)
        );
    }
}
