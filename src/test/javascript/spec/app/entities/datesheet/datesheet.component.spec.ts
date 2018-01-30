/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { Observable } from 'rxjs/Observable';
import { Headers } from '@angular/http';

import { B2CTestModule } from '../../../test.module';
import { DatesheetComponent } from '../../../../../../main/webapp/app/entities/datesheet/datesheet.component';
import { DatesheetService } from '../../../../../../main/webapp/app/entities/datesheet/datesheet.service';
import { Datesheet } from '../../../../../../main/webapp/app/entities/datesheet/datesheet.model';

describe('Component Tests', () => {

    describe('Datesheet Management Component', () => {
        let comp: DatesheetComponent;
        let fixture: ComponentFixture<DatesheetComponent>;
        let service: DatesheetService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [B2CTestModule],
                declarations: [DatesheetComponent],
                providers: [
                    DatesheetService
                ]
            })
            .overrideTemplate(DatesheetComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(DatesheetComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(DatesheetService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN
                const headers = new Headers();
                headers.append('link', 'link;link');
                spyOn(service, 'query').and.returnValue(Observable.of({
                    json: [new Datesheet(123)],
                    headers
                }));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.query).toHaveBeenCalled();
                expect(comp.datesheets[0]).toEqual(jasmine.objectContaining({id: 123}));
            });
        });
    });

});
