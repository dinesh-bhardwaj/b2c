/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable } from 'rxjs/Observable';
import { JhiEventManager } from 'ng-jhipster';

import { B2CTestModule } from '../../../test.module';
import { SchoolClassDialogComponent } from '../../../../../../main/webapp/app/entities/school-class/school-class-dialog.component';
import { SchoolClassService } from '../../../../../../main/webapp/app/entities/school-class/school-class.service';
import { SchoolClass } from '../../../../../../main/webapp/app/entities/school-class/school-class.model';

describe('Component Tests', () => {

    describe('SchoolClass Management Dialog Component', () => {
        let comp: SchoolClassDialogComponent;
        let fixture: ComponentFixture<SchoolClassDialogComponent>;
        let service: SchoolClassService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [B2CTestModule],
                declarations: [SchoolClassDialogComponent],
                providers: [
                    SchoolClassService
                ]
            })
            .overrideTemplate(SchoolClassDialogComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(SchoolClassDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(SchoolClassService);
            mockEventManager = fixture.debugElement.injector.get(JhiEventManager);
            mockActiveModal = fixture.debugElement.injector.get(NgbActiveModal);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity',
                inject([],
                    fakeAsync(() => {
                        // GIVEN
                        const entity = new SchoolClass(123);
                        spyOn(service, 'update').and.returnValue(Observable.of(entity));
                        comp.schoolClass = entity;
                        // WHEN
                        comp.save();
                        tick(); // simulate async

                        // THEN
                        expect(service.update).toHaveBeenCalledWith(entity);
                        expect(comp.isSaving).toEqual(false);
                        expect(mockEventManager.broadcastSpy).toHaveBeenCalledWith({ name: 'schoolClassListModification', content: 'OK'});
                        expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
                    })
                )
            );

            it('Should call create service on save for new entity',
                inject([],
                    fakeAsync(() => {
                        // GIVEN
                        const entity = new SchoolClass();
                        spyOn(service, 'create').and.returnValue(Observable.of(entity));
                        comp.schoolClass = entity;
                        // WHEN
                        comp.save();
                        tick(); // simulate async

                        // THEN
                        expect(service.create).toHaveBeenCalledWith(entity);
                        expect(comp.isSaving).toEqual(false);
                        expect(mockEventManager.broadcastSpy).toHaveBeenCalledWith({ name: 'schoolClassListModification', content: 'OK'});
                        expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
                    })
                )
            );
        });
    });

});
