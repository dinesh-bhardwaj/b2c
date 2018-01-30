/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { Observable } from 'rxjs/Observable';
import { Headers } from '@angular/http';

import { B2CTestModule } from '../../../test.module';
import { SchoolClassComponent } from '../../../../../../main/webapp/app/entities/school-class/school-class.component';
import { SchoolClassService } from '../../../../../../main/webapp/app/entities/school-class/school-class.service';
import { SchoolClass } from '../../../../../../main/webapp/app/entities/school-class/school-class.model';

describe('Component Tests', () => {

    describe('SchoolClass Management Component', () => {
        let comp: SchoolClassComponent;
        let fixture: ComponentFixture<SchoolClassComponent>;
        let service: SchoolClassService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [B2CTestModule],
                declarations: [SchoolClassComponent],
                providers: [
                    SchoolClassService
                ]
            })
            .overrideTemplate(SchoolClassComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(SchoolClassComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(SchoolClassService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN
                const headers = new Headers();
                headers.append('link', 'link;link');
                spyOn(service, 'query').and.returnValue(Observable.of({
                    json: [new SchoolClass(123)],
                    headers
                }));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.query).toHaveBeenCalled();
                expect(comp.schoolClasses[0]).toEqual(jasmine.objectContaining({id: 123}));
            });
        });
    });

});
