import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ProveedorModal } from './proveedor-modal';

describe('ProveedorModal', () => {
  let component: ProveedorModal;
  let fixture: ComponentFixture<ProveedorModal>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ProveedorModal],
    }).compileComponents();

    fixture = TestBed.createComponent(ProveedorModal);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
