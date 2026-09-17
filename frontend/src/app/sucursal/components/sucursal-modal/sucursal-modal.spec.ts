import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SucursalModal } from './sucursal-modal';

describe('SucursalModal', () => {
  let component: SucursalModal;
  let fixture: ComponentFixture<SucursalModal>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [SucursalModal],
    }).compileComponents();

    fixture = TestBed.createComponent(SucursalModal);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
