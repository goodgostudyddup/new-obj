<template>
    <nav class="px-4 border-b">
        <nav class="h-14 flex items-center justify-end">

            <Menu v-slot="{ open }">
                <MenuButton as="template">
                    <md-standard-icon-button>
                        <md-icon>settings</md-icon>
                    </md-standard-icon-button>
                </MenuButton>
                <MenuItems v-show="open" class="absolute top-14 bg-white border rounded-md flex">
                    <MenuItem>
                    </MenuItem>
                </MenuItems>
            </Menu>
        </nav>
    </nav>
</template>

<script setup lang="ts">
import { Menu, MenuButton, MenuItems, MenuItem } from '@headlessui/vue'

</script>

<style scoped></style>