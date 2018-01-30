/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { Observable } from 'rxjs/Observable';
import { Headers } from '@angular/http';

import { B2CTestModule } from '../../../test.module';
import { SessionComponent } from '../../../../../../main/webapp/app/entities/session/session.component';
import { SessionService } from '../../../../../../main/webapp/app/entities/session/session.service';
import { Session } from '../../../../../../main/webapp/app/entities/session/session.model';

describe('Component Tests', () => {

    describe('Session Management Component', () => {
        let comp: SessionComponent;
        let fixture: ComponentFixture<SessionComponent>;
        let service: SessionService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [B2CTestModule],
                declarations: [SessionComponent],
                providers: [
                    SessionService
                ]
            })
            .overrideTemplate(SessionComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(SessionComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(SessionService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN
                const headers = new Headers();
                headers.append('link', 'link;link');
                spyOn(service, 'query').and.returnValue(Observable.of({
                    json: [new Session(123)],
                    headers
                }));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.query).toHaveBeenCalled();
                expect(comp.sessions[0]).toEqual(jasmine.objectContaining({id: 123}));
            });
        });
    });

});
