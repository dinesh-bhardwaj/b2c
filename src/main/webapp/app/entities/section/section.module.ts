import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { B2CSharedModule } from '../../shared';
import {
    SectionService,
    SectionPopupService,
    SectionComponent,
    SectionDetailComponent,
    SectionDialogComponent,
    SectionPopupComponent,
    SectionDeletePopupComponent,
    SectionDeleteDialogComponent,
    sectionRoute,
    sectionPopupRoute,
} from './';

const ENTITY_STATES = [
    ...sectionRoute,
    ...sectionPopupRoute,
];

@NgModule({
    imports: [
        B2CSharedModule,
        RouterModule.forChild(ENTITY_STATES)
    ],
    declarations: [
        SectionComponent,
        SectionDetailComponent,
        SectionDialogComponent,
        SectionDeleteDialogComponent,
        SectionPopupComponent,
        SectionDeletePopupComponent,
    ],
    entryComponents: [
        SectionComponent,
        SectionDialogComponent,
        SectionPopupComponent,
        SectionDeleteDialogComponent,
        SectionDeletePopupComponent,
    ],
    providers: [
        SectionService,
        SectionPopupService,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class B2CSectionModule {}
