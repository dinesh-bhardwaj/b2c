/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable } from 'rxjs/Observable';
import { JhiEventManager } from 'ng-jhipster';

import { B2CTestModule } from '../../../test.module';
import { AttendanceDialogComponent } from '../../../../../../main/webapp/app/entities/attendance/attendance-dialog.component';
import { AttendanceService } from '../../../../../../main/webapp/app/entities/attendance/attendance.service';
import { Attendance } from '../../../../../../main/webapp/app/entities/attendance/attendance.model';
import { StudentService } from '../../../../../../main/webapp/app/entities/student';

describe('Component Tests', () => {

    describe('Attendance Management Dialog Component', () => {
        let comp: AttendanceDialogComponent;
        let fixture: ComponentFixture<AttendanceDialogComponent>;
        let service: AttendanceService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [B2CTestModule],
                declarations: [AttendanceDialogComponent],
                providers: [
                    StudentService,
                    AttendanceService
                ]
            })
            .overrideTemplate(AttendanceDialogComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(AttendanceDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(AttendanceService);
            mockEventManager = fixture.debugElement.injector.get(JhiEventManager);
            mockActiveModal = fixture.debugElement.injector.get(NgbActiveModal);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity',
                inject([],
                    fakeAsync(() => {
                        // GIVEN
                        const entity = new Attendance(123);
                        spyOn(service, 'update').and.returnValue(Observable.of(entity));
                        comp.attendance = entity;
                        // WHEN
                        comp.save();
                        tick(); // simulate async

                        // THEN
                        expect(service.update).toHaveBeenCalledWith(entity);
                        expect(comp.isSaving).toEqual(false);
                        expect(mockEventManager.broadcastSpy).toHaveBeenCalledWith({ name: 'attendanceListModification', content: 'OK'});
                        expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
                    })
                )
            );

            it('Should call create service on save for new entity',
                inject([],
                    fakeAsync(() => {
                        // GIVEN
                        const entity = new Attendance();
                        spyOn(service, 'create').and.returnValue(Observable.of(entity));
                        comp.attendance = entity;
                        // WHEN
                        comp.save();
                        tick(); // simulate async

                        // THEN
                        expect(service.create).toHaveBeenCalledWith(entity);
                        expect(comp.isSaving).toEqual(false);
                        expect(mockEventManager.broadcastSpy).toHaveBeenCalledWith({ name: 'attendanceListModification', content: 'OK'});
                        expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
                    })
                )
            );
        });
    });

});
