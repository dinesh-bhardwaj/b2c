/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { Observable } from 'rxjs/Observable';

import { B2CTestModule } from '../../../test.module';
import { AttendanceDetailComponent } from '../../../../../../main/webapp/app/entities/attendance/attendance-detail.component';
import { AttendanceService } from '../../../../../../main/webapp/app/entities/attendance/attendance.service';
import { Attendance } from '../../../../../../main/webapp/app/entities/attendance/attendance.model';

describe('Component Tests', () => {

    describe('Attendance Management Detail Component', () => {
        let comp: AttendanceDetailComponent;
        let fixture: ComponentFixture<AttendanceDetailComponent>;
        let service: AttendanceService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [B2CTestModule],
                declarations: [AttendanceDetailComponent],
                providers: [
                    AttendanceService
                ]
            })
            .overrideTemplate(AttendanceDetailComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(AttendanceDetailComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(AttendanceService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                spyOn(service, 'find').and.returnValue(Observable.of(new Attendance(123)));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.find).toHaveBeenCalledWith(123);
                expect(comp.attendance).toEqual(jasmine.objectContaining({id: 123}));
            });
        });
    });

});
