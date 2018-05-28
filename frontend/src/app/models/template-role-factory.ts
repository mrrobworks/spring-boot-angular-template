import { TemplateRole } from './template-role';
import { TemplateRoleRaw } from './template-role-raw';

export class TemplateRoleFactory {
  static empty(): TemplateRole {
    return new TemplateRole('', '', '');
  }

  static fromObject(templateRoleRaw: TemplateRoleRaw) {
    return new TemplateRole(
      templateRoleRaw.id,
      templateRoleRaw.description,
      templateRoleRaw.authority
    );
  }
}
