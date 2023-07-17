<template>
    <div>
        <header>
            <Display msg="首页"></Display>
        </header>


        <TabGroup>
            <TabList as="md-tabs">
                <Tab as="md-tab">Overview</Tab>
                <Tab as="md-tab">Pocket</Tab>
            </TabList>
            <TabPanels>
                <TabPanel>Developing
                </TabPanel>
                <TabPanel>Developing too
                </TabPanel>
            </TabPanels>
        </TabGroup>


        <nav class="fixed bottom-8 right-8 z-30">
            <PocketBookCreatorFab @click="isOpen.set(true)"></PocketBookCreatorFab>
        </nav>

        <Window fixed fullscreen :is-open="isOpen.get()" :set-is-open="isOpen.set">
            <PocketBookCreator></PocketBookCreator>
        </Window>
    </div>
</template>

<script setup lang="ts">
import { TabGroup, TabList, Tab, TabPanels, TabPanel } from '@headlessui/vue'
import PocketBookCreatorFab from '@/components/PocketBookCreatorFab.vue';
import PocketBookCreator from '@/components/PocketBookCreator.vue';
import { Display } from '@/components/Text';
import { useState } from '@/hooks/useState';
import Window from '@/components/Window.vue'

const isOpen = useState(false)
</script>

<style scoped>
.rotate {
    animation: rotateAnimate 4s infinite linear;
    transform: scale(6);
}

@keyframes rotateAnimate {
    from {
        transform: rotate(0deg) scale(6);
    }

    to {
        transform: rotate(360deg) scale(6);
    }
}
</style>