/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { Observable } from 'rxjs/Observable';
import { Headers } from '@angular/http';

import { B2CTestModule } from '../../../test.module';
import { AttendanceComponent } from '../../../../../../main/webapp/app/entities/attendance/attendance.component';
import { AttendanceService } from '../../../../../../main/webapp/app/entities/attendance/attendance.service';
import { Attendance } from '../../../../../../main/webapp/app/entities/attendance/attendance.model';

describe('Component Tests', () => {

    describe('Attendance Management Component', () => {
        let comp: AttendanceComponent;
        let fixture: ComponentFixture<AttendanceComponent>;
        let service: AttendanceService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [B2CTestModule],
                declarations: [AttendanceComponent],
                providers: [
                    AttendanceService
                ]
            })
            .overrideTemplate(AttendanceComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(AttendanceComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(AttendanceService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN
                const headers = new Headers();
                headers.append('link', 'link;link');
                spyOn(service, 'query').and.returnValue(Observable.of({
                    json: [new Attendance(123)],
                    headers
                }));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.query).toHaveBeenCalled();
                expect(comp.attendances[0]).toEqual(jasmine.objectContaining({id: 123}));
            });
        });
    });

});
