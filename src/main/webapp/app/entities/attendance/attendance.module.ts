import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { B2CSharedModule } from '../../shared';
import {
    AttendanceService,
    AttendancePopupService,
    AttendanceComponent,
    AttendanceDetailComponent,
    AttendanceDialogComponent,
    AttendancePopupComponent,
    AttendanceDeletePopupComponent,
    AttendanceDeleteDialogComponent,
    attendanceRoute,
    attendancePopupRoute,
} from './';

const ENTITY_STATES = [
    ...attendanceRoute,
    ...attendancePopupRoute,
];

@NgModule({
    imports: [
        B2CSharedModule,
        RouterModule.forChild(ENTITY_STATES)
    ],
    declarations: [
        AttendanceComponent,
        AttendanceDetailComponent,
        AttendanceDialogComponent,
        AttendanceDeleteDialogComponent,
        AttendancePopupComponent,
        AttendanceDeletePopupComponent,
    ],
    entryComponents: [
        AttendanceComponent,
        AttendanceDialogComponent,
        AttendancePopupComponent,
        AttendanceDeleteDialogComponent,
        AttendanceDeletePopupComponent,
    ],
    providers: [
        AttendanceService,
        AttendancePopupService,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class B2CAttendanceModule {}
