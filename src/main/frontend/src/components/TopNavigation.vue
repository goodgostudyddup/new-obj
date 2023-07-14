<template>
    <nav class="flex flex-row items-center justify-between px-4 py-2 bg-[var(--md-sys-color-background)]">
        <div class="flex items-center gap-4">
            <OpenNavigationRailButton></OpenNavigationRailButton>
            
            <Title></Title>
        </div>
        
        <nav class="flex items-center justify-end">
            <Menu v-slot="{ open }">
                <MenuButton as="template">
                    <md-standard-icon-button>
                        <md-icon>settings</md-icon>
                    </md-standard-icon-button>
                </MenuButton>
                <MenuItems v-show="open" class="absolute top-14 border rounded-md flex">
                    <MenuItem>
                    </MenuItem>
                </MenuItems>
            </Menu>
        </nav>
    </nav>
</template>

<script setup lang="tsx">
import { Menu, MenuButton, MenuItems, MenuItem } from '@headlessui/vue'
import OpenNavigationRailButton from './OpenNavigationRailButton.vue';

const Title = () => (
    <div>
        <h1 class="font-semibold">记账小工具</h1>
        <p class="text-xs">减少你的心智负担</p>
    </div>
)
</script>

<style scoped></style>