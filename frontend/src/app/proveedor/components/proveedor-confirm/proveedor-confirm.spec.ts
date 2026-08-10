import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ProveedorConfirm } from './proveedor-confirm';

describe('ProveedorConfirm', () => {
  let component: ProveedorConfirm;
  let fixture: ComponentFixture<ProveedorConfirm>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ProveedorConfirm],
    }).compileComponents();

    fixture = TestBed.createComponent(ProveedorConfirm);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
