export interface TemplateUserRaw {
  id: string;
  roles: {
    id: string;
    description: string;
    authority: string;
  }[];
}
