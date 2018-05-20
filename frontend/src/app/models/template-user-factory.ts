import { TemplateUserRaw } from './template-user-raw';
import { TemplateUser } from './template-user';

export class TemplateUserFactory {
  static empty(): TemplateUser {
    return new TemplateUser(
      '', [{id: '', description: '', authority: ''}]
    )
  }

  static fromObject(templateUserRaw: TemplateUserRaw): TemplateUser {
    return new TemplateUser(templateUserRaw.id, templateUserRaw.roles);
  }
}
