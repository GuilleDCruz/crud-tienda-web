import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SucursalConfirm } from './sucursal-confirm';

describe('SucursalConfirm', () => {
  let component: SucursalConfirm;
  let fixture: ComponentFixture<SucursalConfirm>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [SucursalConfirm],
    }).compileComponents();

    fixture = TestBed.createComponent(SucursalConfirm);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
