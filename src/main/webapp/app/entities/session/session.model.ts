import { BaseEntity } from './../../shared';

export class Session implements BaseEntity {
    constructor(
        public id?: number,
        public name?: string,
    ) {
    }
}
