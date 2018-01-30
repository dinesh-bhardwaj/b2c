import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { B2CSharedModule } from '../../shared';
import {
    DatesheetService,
    DatesheetPopupService,
    DatesheetComponent,
    DatesheetDetailComponent,
    DatesheetDialogComponent,
    DatesheetPopupComponent,
    DatesheetDeletePopupComponent,
    DatesheetDeleteDialogComponent,
    datesheetRoute,
    datesheetPopupRoute,
} from './';

const ENTITY_STATES = [
    ...datesheetRoute,
    ...datesheetPopupRoute,
];

@NgModule({
    imports: [
        B2CSharedModule,
        RouterModule.forChild(ENTITY_STATES)
    ],
    declarations: [
        DatesheetComponent,
        DatesheetDetailComponent,
        DatesheetDialogComponent,
        DatesheetDeleteDialogComponent,
        DatesheetPopupComponent,
        DatesheetDeletePopupComponent,
    ],
    entryComponents: [
        DatesheetComponent,
        DatesheetDialogComponent,
        DatesheetPopupComponent,
        DatesheetDeleteDialogComponent,
        DatesheetDeletePopupComponent,
    ],
    providers: [
        DatesheetService,
        DatesheetPopupService,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class B2CDatesheetModule {}
