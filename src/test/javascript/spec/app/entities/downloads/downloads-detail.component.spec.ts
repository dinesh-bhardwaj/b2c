/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { Observable } from 'rxjs/Observable';

import { B2CTestModule } from '../../../test.module';
import { DownloadsDetailComponent } from '../../../../../../main/webapp/app/entities/downloads/downloads-detail.component';
import { DownloadsService } from '../../../../../../main/webapp/app/entities/downloads/downloads.service';
import { Downloads } from '../../../../../../main/webapp/app/entities/downloads/downloads.model';

describe('Component Tests', () => {

    describe('Downloads Management Detail Component', () => {
        let comp: DownloadsDetailComponent;
        let fixture: ComponentFixture<DownloadsDetailComponent>;
        let service: DownloadsService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [B2CTestModule],
                declarations: [DownloadsDetailComponent],
                providers: [
                    DownloadsService
                ]
            })
            .overrideTemplate(DownloadsDetailComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(DownloadsDetailComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(DownloadsService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                spyOn(service, 'find').and.returnValue(Observable.of(new Downloads(123)));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.find).toHaveBeenCalledWith(123);
                expect(comp.downloads).toEqual(jasmine.objectContaining({id: 123}));
            });
        });
    });

});
