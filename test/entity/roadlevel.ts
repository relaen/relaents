
import {Road} from './road'
import { Entity, Id, Column, OneToMany } from '../../core/decorator/decorator';
import { BaseEntity } from '../../core/entity';
import { EFkConstraint } from '../../core/entitydefine';

@Entity("t_road_level",'tunnel')
export class RoadLevel extends BaseEntity{
	@Id()
	@Column({
		name:'road_level_id',
		type:'int',
		nullable:false
	})
	roadLevelId:number;

	@Column({
		name:'road_level_name',
		type:'string',
		nullable:true
	})
	roadLevelName:string;

	@OneToMany({entity:'Road',onDelete:EFkConstraint.SETNULL,onUpdate:EFkConstraint.CASCADE,mappedBy:'roadLevel',lazyFetch:true})
	roads:Array<Road>;

}