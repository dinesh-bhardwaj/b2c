/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { Observable } from 'rxjs/Observable';

import { B2CTestModule } from '../../../test.module';
import { DatesheetDetailComponent } from '../../../../../../main/webapp/app/entities/datesheet/datesheet-detail.component';
import { DatesheetService } from '../../../../../../main/webapp/app/entities/datesheet/datesheet.service';
import { Datesheet } from '../../../../../../main/webapp/app/entities/datesheet/datesheet.model';

describe('Component Tests', () => {

    describe('Datesheet Management Detail Component', () => {
        let comp: DatesheetDetailComponent;
        let fixture: ComponentFixture<DatesheetDetailComponent>;
        let service: DatesheetService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [B2CTestModule],
                declarations: [DatesheetDetailComponent],
                providers: [
                    DatesheetService
                ]
            })
            .overrideTemplate(DatesheetDetailComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(DatesheetDetailComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(DatesheetService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                spyOn(service, 'find').and.returnValue(Observable.of(new Datesheet(123)));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.find).toHaveBeenCalledWith(123);
                expect(comp.datesheet).toEqual(jasmine.objectContaining({id: 123}));
            });
        });
    });

});
