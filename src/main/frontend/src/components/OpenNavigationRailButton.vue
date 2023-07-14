<template>
    <div class="flex justify-center items-center h-14">
        <md-standard-icon-button @click="navStore.toggleisOpen">
            <md-icon>menu</md-icon>
        </md-standard-icon-button>
    </div>
</template>

<script setup lang="ts">
import { useNavigationRailStore } from '@/store/useNavigationRail';


const navStore = useNavigationRailStore()

</script>

<style scoped></style>