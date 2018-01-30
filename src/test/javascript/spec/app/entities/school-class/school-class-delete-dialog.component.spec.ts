/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable } from 'rxjs/Observable';
import { JhiEventManager } from 'ng-jhipster';

import { B2CTestModule } from '../../../test.module';
import { SchoolClassDeleteDialogComponent } from '../../../../../../main/webapp/app/entities/school-class/school-class-delete-dialog.component';
import { SchoolClassService } from '../../../../../../main/webapp/app/entities/school-class/school-class.service';

describe('Component Tests', () => {

    describe('SchoolClass Management Delete Component', () => {
        let comp: SchoolClassDeleteDialogComponent;
        let fixture: ComponentFixture<SchoolClassDeleteDialogComponent>;
        let service: SchoolClassService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [B2CTestModule],
                declarations: [SchoolClassDeleteDialogComponent],
                providers: [
                    SchoolClassService
                ]
            })
            .overrideTemplate(SchoolClassDeleteDialogComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(SchoolClassDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(SchoolClassService);
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
