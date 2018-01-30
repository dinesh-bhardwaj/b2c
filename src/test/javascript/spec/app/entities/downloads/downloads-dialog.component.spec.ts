/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable } from 'rxjs/Observable';
import { JhiEventManager } from 'ng-jhipster';

import { B2CTestModule } from '../../../test.module';
import { DownloadsDialogComponent } from '../../../../../../main/webapp/app/entities/downloads/downloads-dialog.component';
import { DownloadsService } from '../../../../../../main/webapp/app/entities/downloads/downloads.service';
import { Downloads } from '../../../../../../main/webapp/app/entities/downloads/downloads.model';
import { SchoolClassService } from '../../../../../../main/webapp/app/entities/school-class';
import { SectionService } from '../../../../../../main/webapp/app/entities/section';

describe('Component Tests', () => {

    describe('Downloads Management Dialog Component', () => {
        let comp: DownloadsDialogComponent;
        let fixture: ComponentFixture<DownloadsDialogComponent>;
        let service: DownloadsService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [B2CTestModule],
                declarations: [DownloadsDialogComponent],
                providers: [
                    SchoolClassService,
                    SectionService,
                    DownloadsService
                ]
            })
            .overrideTemplate(DownloadsDialogComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(DownloadsDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(DownloadsService);
            mockEventManager = fixture.debugElement.injector.get(JhiEventManager);
            mockActiveModal = fixture.debugElement.injector.get(NgbActiveModal);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity',
                inject([],
                    fakeAsync(() => {
                        // GIVEN
                        const entity = new Downloads(123);
                        spyOn(service, 'update').and.returnValue(Observable.of(entity));
                        comp.downloads = entity;
                        // WHEN
                        comp.save();
                        tick(); // simulate async

                        // THEN
                        expect(service.update).toHaveBeenCalledWith(entity);
                        expect(comp.isSaving).toEqual(false);
                        expect(mockEventManager.broadcastSpy).toHaveBeenCalledWith({ name: 'downloadsListModification', content: 'OK'});
                        expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
                    })
                )
            );

            it('Should call create service on save for new entity',
                inject([],
                    fakeAsync(() => {
                        // GIVEN
                        const entity = new Downloads();
                        spyOn(service, 'create').and.returnValue(Observable.of(entity));
                        comp.downloads = entity;
                        // WHEN
                        comp.save();
                        tick(); // simulate async

                        // THEN
                        expect(service.create).toHaveBeenCalledWith(entity);
                        expect(comp.isSaving).toEqual(false);
                        expect(mockEventManager.broadcastSpy).toHaveBeenCalledWith({ name: 'downloadsListModification', content: 'OK'});
                        expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
                    })
                )
            );
        });
    });

});
