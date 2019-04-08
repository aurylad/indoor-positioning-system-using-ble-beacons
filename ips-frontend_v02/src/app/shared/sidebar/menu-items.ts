import { RouteInfo } from './sidebar.metadata';

export const ROUTES: RouteInfo[] = [
  {
    path: '',
    title: 'Meniu',
    icon: 'mdi mdi-dots-horizontal',
    class: 'nav-small-cap',
    extralink: true,
    submenu: []
  },
  {
    path: '/starter',
    title: 'Pagrindinis',
    icon: 'mdi mdi-gauge',
    class: '',
    extralink: false,
    submenu: []
  },
  {
    path: '/component/plan-upload',
    title: 'Plano įkėlimas',
    icon: 'mdi mdi-google-maps',
    class: '',
    extralink: false,
    submenu: []
  },
  {
    path: '/component/beacon-reg',
    title: 'Siųstuvų registracija',
    icon: 'mdi mdi-google-circles-extended',
    class: '',
    extralink: false,
    submenu: []
  },
  {
    path: '/component/object-reg',
    title: 'Objekto registracija',
    icon: 'mdi mdi-account-box',                      
    class: '',
    extralink: false,
    submenu: []
  },
  {
    path: '/component/object-rendering',
    title: 'Objektų atvaizdavimas',
    icon: 'mdi mdi-monitor',
    class: '',
    extralink: false,
    submenu: []
  },
  {
    path: '/component/real-time-rendering',
    title: 'Stebėti objektus',
    icon: 'mdi mdi-message-video',
    class: '',
    extralink: false,
    submenu: []
  },
  {
    path: '/component/restricted-area-reg',
    title: 'Draudžiamos zonos',
    icon: 'mdi mdi-minus-circle-outline',
    class: '',
    extralink: false,
    submenu: []
  },
  {
    path: '/component/violations',
    title: 'Užregistruoti pažeidimai',
    icon: 'mdi mdi-clipboard-text',
    class: '',
    extralink: false,
    submenu: []
  },
  {
    path: '',
    title: 'UI Components',
    icon: 'mdi mdi-dots-horizontal',
    class: 'nav-small-cap',
    extralink: true,
    submenu: []
  },
  {
    path: '/component/accordion',
    title: 'Accordion',
    icon: 'mdi mdi-equal',
    class: '',
    extralink: false,
    submenu: []
  },
  {
    path: '/component/alert',
    title: 'Alert',
    icon: 'mdi mdi-message-bulleted',
    class: '',
    extralink: false,
    submenu: []
  },
  {
    path: '/component/carousel',
    title: 'Carousel',
    icon: 'mdi mdi-view-carousel',
    class: '',
    extralink: false,
    submenu: []
  },
  {
    path: '/component/dropdown',
    title: 'Dropdown',
    icon: 'mdi mdi-calendar-clock',
    class: '',
    extralink: false,
    submenu: []
  },
  {
    path: '/component/modal',
    title: 'Modal',
    icon: 'mdi mdi-tablet',
    class: '',
    extralink: false,
    submenu: []
  },
  {
    path: '/component/pagination',
    title: 'Pagination',
    icon: 'mdi mdi-backburger',
    class: '',
    extralink: false,
    submenu: []
  },
  {
    path: '/component/poptool',
    title: 'Popover & Tooltip',
    icon: 'mdi mdi-image-filter-vintage',
    class: '',
    extralink: false,
    submenu: []
  },
  {
    path: '/component/progressbar',
    title: 'Progressbar',
    icon: 'mdi mdi-poll',
    class: '',
    extralink: false,
    submenu: []
  },
  {
    path: '/component/rating',
    title: 'Ratings',
    icon: 'mdi mdi-bandcamp',
    class: '',
    extralink: false,
    submenu: []
  },
  {
    path: '/component/tabs',
    title: 'Tabs',
    icon: 'mdi mdi-sort-variant',
    class: '',
    extralink: false,
    submenu: []
  },
  {
    path: '/component/timepicker',
    title: 'Timepicker',
    icon: 'mdi mdi-calendar-clock',
    class: '',
    extralink: false,
    submenu: []
  }
];
