import { BaseEntity } from './../../shared';

export class SchoolClass implements BaseEntity {
    constructor(
        public id?: number,
        public name?: string,
    ) {
    }
}
