/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable } from 'rxjs/Observable';
import { JhiEventManager } from 'ng-jhipster';

import { B2CTestModule } from '../../../test.module';
import { DownloadsDeleteDialogComponent } from '../../../../../../main/webapp/app/entities/downloads/downloads-delete-dialog.component';
import { DownloadsService } from '../../../../../../main/webapp/app/entities/downloads/downloads.service';

describe('Component Tests', () => {

    describe('Downloads Management Delete Component', () => {
        let comp: DownloadsDeleteDialogComponent;
        let fixture: ComponentFixture<DownloadsDeleteDialogComponent>;
        let service: DownloadsService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [B2CTestModule],
                declarations: [DownloadsDeleteDialogComponent],
                providers: [
                    DownloadsService
                ]
            })
            .overrideTemplate(DownloadsDeleteDialogComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(DownloadsDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(DownloadsService);
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
