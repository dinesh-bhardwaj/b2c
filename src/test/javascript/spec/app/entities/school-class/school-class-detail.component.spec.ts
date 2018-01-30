/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { Observable } from 'rxjs/Observable';

import { B2CTestModule } from '../../../test.module';
import { SchoolClassDetailComponent } from '../../../../../../main/webapp/app/entities/school-class/school-class-detail.component';
import { SchoolClassService } from '../../../../../../main/webapp/app/entities/school-class/school-class.service';
import { SchoolClass } from '../../../../../../main/webapp/app/entities/school-class/school-class.model';

describe('Component Tests', () => {

    describe('SchoolClass Management Detail Component', () => {
        let comp: SchoolClassDetailComponent;
        let fixture: ComponentFixture<SchoolClassDetailComponent>;
        let service: SchoolClassService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [B2CTestModule],
                declarations: [SchoolClassDetailComponent],
                providers: [
                    SchoolClassService
                ]
            })
            .overrideTemplate(SchoolClassDetailComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(SchoolClassDetailComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(SchoolClassService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                spyOn(service, 'find').and.returnValue(Observable.of(new SchoolClass(123)));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.find).toHaveBeenCalledWith(123);
                expect(comp.schoolClass).toEqual(jasmine.objectContaining({id: 123}));
            });
        });
    });

});
