/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable } from 'rxjs/Observable';
import { JhiEventManager } from 'ng-jhipster';

import { B2CTestModule } from '../../../test.module';
import { DatesheetDialogComponent } from '../../../../../../main/webapp/app/entities/datesheet/datesheet-dialog.component';
import { DatesheetService } from '../../../../../../main/webapp/app/entities/datesheet/datesheet.service';
import { Datesheet } from '../../../../../../main/webapp/app/entities/datesheet/datesheet.model';
import { SchoolClassService } from '../../../../../../main/webapp/app/entities/school-class';
import { SectionService } from '../../../../../../main/webapp/app/entities/section';

describe('Component Tests', () => {

    describe('Datesheet Management Dialog Component', () => {
        let comp: DatesheetDialogComponent;
        let fixture: ComponentFixture<DatesheetDialogComponent>;
        let service: DatesheetService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [B2CTestModule],
                declarations: [DatesheetDialogComponent],
                providers: [
                    SchoolClassService,
                    SectionService,
                    DatesheetService
                ]
            })
            .overrideTemplate(DatesheetDialogComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(DatesheetDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(DatesheetService);
            mockEventManager = fixture.debugElement.injector.get(JhiEventManager);
            mockActiveModal = fixture.debugElement.injector.get(NgbActiveModal);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity',
                inject([],
                    fakeAsync(() => {
                        // GIVEN
                        const entity = new Datesheet(123);
                        spyOn(service, 'update').and.returnValue(Observable.of(entity));
                        comp.datesheet = entity;
                        // WHEN
                        comp.save();
                        tick(); // simulate async

                        // THEN
                        expect(service.update).toHaveBeenCalledWith(entity);
                        expect(comp.isSaving).toEqual(false);
                        expect(mockEventManager.broadcastSpy).toHaveBeenCalledWith({ name: 'datesheetListModification', content: 'OK'});
                        expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
                    })
                )
            );

            it('Should call create service on save for new entity',
                inject([],
                    fakeAsync(() => {
                        // GIVEN
                        const entity = new Datesheet();
                        spyOn(service, 'create').and.returnValue(Observable.of(entity));
                        comp.datesheet = entity;
                        // WHEN
                        comp.save();
                        tick(); // simulate async

                        // THEN
                        expect(service.create).toHaveBeenCalledWith(entity);
                        expect(comp.isSaving).toEqual(false);
                        expect(mockEventManager.broadcastSpy).toHaveBeenCalledWith({ name: 'datesheetListModification', content: 'OK'});
                        expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
                    })
                )
            );
        });
    });

});
