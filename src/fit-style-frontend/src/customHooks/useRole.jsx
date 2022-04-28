import {useAuth} from "../packages/auth/useAuth";

export const useRole = (initialRole) => {
  const {roles} = useAuth();
  return roles.indexOf(`ROLE_${initialRole}`) !== -1
}