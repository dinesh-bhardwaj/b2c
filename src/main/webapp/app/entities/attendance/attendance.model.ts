import { BaseEntity } from './../../shared';

export const enum MonthFields {
    'JAN',
    'FEB',
    'MAR',
    'APR',
    'MAY',
    'JUN',
    'JUL',
    'AUG',
    'SEP',
    'OCT',
    'NOV',
    'DEC'
}

export const enum AttendanceField {
    'PRESENT',
    'ABSENT',
    'LEAVE',
    'LATE_FOR_SCHOOL',
    'HOLIDAY',
    'SUNDAY_HOLIDAY',
    'WEEKLY_HOLIDAY',
    'WEEKLY_OFF'
}

export class Attendance implements BaseEntity {
    constructor(
        public id?: number,
        public month?: MonthFields,
        public date?: any,
        public attendance?: AttendanceField,
        public student?: BaseEntity,
    ) {
    }
}
