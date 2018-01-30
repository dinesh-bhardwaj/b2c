/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable } from 'rxjs/Observable';
import { JhiEventManager } from 'ng-jhipster';

import { B2CTestModule } from '../../../test.module';
import { AttendanceDeleteDialogComponent } from '../../../../../../main/webapp/app/entities/attendance/attendance-delete-dialog.component';
import { AttendanceService } from '../../../../../../main/webapp/app/entities/attendance/attendance.service';

describe('Component Tests', () => {

    describe('Attendance Management Delete Component', () => {
        let comp: AttendanceDeleteDialogComponent;
        let fixture: ComponentFixture<AttendanceDeleteDialogComponent>;
        let service: AttendanceService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [B2CTestModule],
                declarations: [AttendanceDeleteDialogComponent],
                providers: [
                    AttendanceService
                ]
            })
            .overrideTemplate(AttendanceDeleteDialogComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(AttendanceDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(AttendanceService);
            mockEventManager = fixture.debugElement.injector.get(JhiEventManager);
            mockActiveModal = fixture.debugElement.injector.get(NgbActiveModal);
        });

        describe('confirmDelete', () => {
            it('Should call delete service on confirmDelete',
                inject([],
                    fakeAsync(() => {
                        // GIVEN
                        spyOn(service, 'delete').and.returnValue(Observable.of({}));

                        // WHEN
                        comp.confirmDelete(123);
                        tick();

                        // THEN
                        expect(service.delete).toHaveBeenCalledWith(123);
                        expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
                        expect(mockEventManager.broadcastSpy).toHaveBeenCalled();
                    })
                )
            );
        });
    });

});
