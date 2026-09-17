import { TestBed } from '@angular/core/testing';
import { SucursalService } from './sucursal';

describe('SucursalService', () => {
    let service: SucursalService;

    beforeEach(() => {
        TestBed.configureTestingModule({});
        service = TestBed.inject(SucursalService);
    });

    it('should be created', () => {
        expect(service).toBeTruthy();
    });
});