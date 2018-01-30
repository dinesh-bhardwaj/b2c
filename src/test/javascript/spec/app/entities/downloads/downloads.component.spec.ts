/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { Observable } from 'rxjs/Observable';
import { Headers } from '@angular/http';

import { B2CTestModule } from '../../../test.module';
import { DownloadsComponent } from '../../../../../../main/webapp/app/entities/downloads/downloads.component';
import { DownloadsService } from '../../../../../../main/webapp/app/entities/downloads/downloads.service';
import { Downloads } from '../../../../../../main/webapp/app/entities/downloads/downloads.model';

describe('Component Tests', () => {

    describe('Downloads Management Component', () => {
        let comp: DownloadsComponent;
        let fixture: ComponentFixture<DownloadsComponent>;
        let service: DownloadsService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [B2CTestModule],
                declarations: [DownloadsComponent],
                providers: [
                    DownloadsService
                ]
            })
            .overrideTemplate(DownloadsComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(DownloadsComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(DownloadsService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN
                const headers = new Headers();
                headers.append('link', 'link;link');
                spyOn(service, 'query').and.returnValue(Observable.of({
                    json: [new Downloads(123)],
                    headers
                }));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.query).toHaveBeenCalled();
                expect(comp.downloads[0]).toEqual(jasmine.objectContaining({id: 123}));
            });
        });
    });

});
