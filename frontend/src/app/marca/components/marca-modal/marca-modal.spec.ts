import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MarcaModal } from './marca-modal';

describe('MarcaModal', () => {
  let component: MarcaModal;
  let fixture: ComponentFixture<MarcaModal>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [MarcaModal],
    }).compileComponents();

    fixture = TestBed.createComponent(MarcaModal);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
