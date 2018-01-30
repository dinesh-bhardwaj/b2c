import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { B2CSharedModule } from '../../shared';
import {
    SchoolClassService,
    SchoolClassPopupService,
    SchoolClassComponent,
    SchoolClassDetailComponent,
    SchoolClassDialogComponent,
    SchoolClassPopupComponent,
    SchoolClassDeletePopupComponent,
    SchoolClassDeleteDialogComponent,
    schoolClassRoute,
    schoolClassPopupRoute,
} from './';

const ENTITY_STATES = [
    ...schoolClassRoute,
    ...schoolClassPopupRoute,
];

@NgModule({
    imports: [
        B2CSharedModule,
        RouterModule.forChild(ENTITY_STATES)
    ],
    declarations: [
        SchoolClassComponent,
        SchoolClassDetailComponent,
        SchoolClassDialogComponent,
        SchoolClassDeleteDialogComponent,
        SchoolClassPopupComponent,
        SchoolClassDeletePopupComponent,
    ],
    entryComponents: [
        SchoolClassComponent,
        SchoolClassDialogComponent,
        SchoolClassPopupComponent,
        SchoolClassDeleteDialogComponent,
        SchoolClassDeletePopupComponent,
    ],
    providers: [
        SchoolClassService,
        SchoolClassPopupService,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class B2CSchoolClassModule {}
